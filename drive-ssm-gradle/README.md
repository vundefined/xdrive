- [接口文档 knife4j](https://github.com/xiaoymin/knife4j)
- [rabbit](http://localhost:15672/#/) `wyrabbit 12345678`

swagger ymal enum

# 代码片段

```
List<String> cacheNames = new ArrayList<>();
cacheNames.add("cachea");
cacheManager.setCacheNames(cacheNames);

@Autowired
private CacheManager cacheManager;

wxMiniService.getDriveLicenseClassTree();
Cache cache = cacheManager.getCache("licenseClass");
System.out.println(cache);
if (!ObjectUtils.isEmpty(cache)) {
    Cache.ValueWrapper wrapper = cache.get("0");
    System.out.println(wrapper);
}

新增用户
http://localhost:8325
/shiro-api-0.0.1/admin/sysUserAdd
{
    "username": "admin",
    "password": "123456",
    "avatar": "admin_avatar/1675917824369_968.jpg",
    "email": "9947033242@qq.com",
    "sort": "4",
    "mobile": "13276605900"
}
```


