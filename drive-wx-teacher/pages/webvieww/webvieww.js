Page({
    data: {
        srcPath: ""
    },
    onLoad(options) {
        this.setData({
            srcPath: options.srcPath
        });
    }
})