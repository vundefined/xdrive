package com.wypaperplane.drivewxmini.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.wypaperplane.drivewxmini.entity.WxUser;
import com.wypaperplane.drivewxmini.entity.WxUserBase;
import com.wypaperplane.drivewxmini.exceptions.WxCodeException;
import com.wypaperplane.drivewxmini.entity.WxUserVo;
import com.wypaperplane.drivewxmini.mapper.WxUserMapper;
import com.wypaperplane.syscore.utils.HttpUtil;
import com.auth0.jwt.JwtUtil;
import com.wypaperplane.syscore.utils.NanoidUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import tk.mybatis.mapper.entity.Example;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class WxAuthService {

    @Autowired
    private WxUserMapper wxUserMapper;

    public Map<String, Object> handleLogin(String code, Byte role, String appid, String secret) {
        StringBuilder url = new StringBuilder();
        url.append("https://api.weixin.qq.com/sns/jscode2session?appid=")
                .append(appid)
                .append("&secret=")
                .append(secret)
                .append("&js_code=")
                .append(code)
                .append("&grant_type=authorization_code");

        JsonNode jsonNode = HttpUtil.mGet(url.toString());
        String openid = jsonNode.get("openid").toString();
        String sessionKey = jsonNode.get("session_key").toString();

        if (openid == null) {
            throw new WxCodeException("code过期");
        }

        Example example = new Example(WxUser.class, true, true);
        example.createCriteria().andEqualTo("openid", openid);
        WxUser wxUser = wxUserMapper.selectOneByExample(example);
        if(ObjectUtils.isEmpty(wxUser)){ // 新用户
            wxUser = new WxUser();
            wxUser.setId(NanoidUtil.generateNanoidId(8));
            wxUser.setOpenid(openid);
            wxUser.setSessionKey(sessionKey);
            wxUser.setRole(role);
            wxUser.setUpdatedTime(LocalDateTime.now());
            wxUser.setDeleted(false); // 不禁用
            wxUserMapper.insertSelective(wxUser);
        } else {
            wxUser.setOpenid(openid);
            wxUser.setSessionKey(sessionKey);
            wxUser.setUpdatedTime(LocalDateTime.now());
            wxUserMapper.updateByPrimaryKeySelective(wxUser);
        }
        // TODO 待优化
        if (wxUser.getDeleted()) {
            // 当该用户被禁用后(直接修改wx_user delete = 1), 需重新登陆后才能生效
            throw new WxCodeException("该用户被禁用");
        } else {
            String jwtToken = JwtUtil.createJWT(wxUser.getId());
            WxUserVo wxUserVo = new WxUserVo();
            BeanUtils.copyProperties(wxUser, wxUserVo);

            Map<String, Object> userInfoMap = new LinkedHashMap<>();
            userInfoMap.put("userInfo", wxUserVo);
            userInfoMap.put("token", jwtToken);
            return userInfoMap;
        }
    }

    public WxUserBase mSelect(Integer wxUserId) {
        WxUser wxUser = wxUserMapper.selectByPrimaryKey(wxUserId);
        WxUserBase wxUserBase = new WxUserBase();
        if (!ObjectUtils.isEmpty(wxUser)) {
            BeanUtils.copyProperties(wxUser, wxUserBase);
        }
        return wxUserBase;
    }

    public void mUpdate(WxUserBase wxUserBase) {
        WxUser wxUser = new WxUser();
        BeanUtils.copyProperties(wxUserBase, wxUser);
        wxUserMapper.updateByPrimaryKeySelective(wxUser);
    }
}
