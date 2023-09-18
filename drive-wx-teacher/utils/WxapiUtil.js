export default {
    downloadFileToPhotosAlbum(_url) {
		wx.downloadFile({
            url: _url,
            success(res) {
                if (res.statusCode === 200) {
                    wx.saveImageToPhotosAlbum({
                        filePath: res.tempFilePath,
                        success(response) {
                            wx.showToast({
                                title: '保存相册成功',
                                icon: 'none'
                            });
                        }
                    });
                }
            }
        });
	}
}