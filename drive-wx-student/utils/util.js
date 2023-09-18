module.exports = {
    testWebSocket() {
        let socketTask = wx.connectSocket({
            url: 'ws://192.168.31.115:15674/ws',
            header:{
              'content-type': 'application/json'
            },
            complete(info) {
                console.log("info------>", info)
            }
        });
        socketTask.onMessage((message) => {
            console.log("message------>", message);
        });
        socketTask.onOpen((open) => {
            console.log("open------>", open);
            socketTask.send({
                data: "Hello",
                complete(info) {
                    console.log("info------>", info)
                }
            });
        });
    }
}