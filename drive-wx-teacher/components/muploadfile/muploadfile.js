import Api from '../../utils/Api.js';
import StorageServer from '../../utils/StorageServer.js';

Component({
    properties: {
        
    },
    data: {
        imgArr: [],
        staticUrl: Api.staticUrl
    },
    methods: {
        uploadImgTap() {
            let that = this;
            if (that.data.imgArr.length >= 5) {
                wx.showToast({
                    title: '最多上传5张图片',
                    icon: 'none'
                });
            } else {
                let _count = 5 - that.data.imgArr.length;
                wx.chooseMedia({
                    count: _count,
                    mediaType: ['image'],
                    success(res) {
                        console.log("--->", res);
                        for (let item of res.tempFiles) {
                            StorageServer.multipleUploadFile(item).then(function(res) {
                                let _imgarr = that.data.imgArr;
                                _imgarr.push(res);
                                that.setData({
                                    imgArr: _imgarr
                                });
                                that.triggerEvent('changeFile', _imgarr);
                            });
                        }
                    },
                })
            }
        },
        deletepic(e) { // 删除上传文件
            let currentindex = e.currentTarget.dataset.index;
            let items = []
            this.data.imgArr.forEach((item, index) => {
                if (index != currentindex) {
                    items.push(item)
                }
            })
            this.setData({
                imgArr: items
            })
            this.triggerEvent('changeFile', items);
        },
    }
})
