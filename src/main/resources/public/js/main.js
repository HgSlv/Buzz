 $.mockjax({
    url: "/pull-requests",
    responseTime: 2500,
    response: function (settings) {
      this.responseText = "<img src='/images/tokyo.png'>"; // Hey, it's just a demo...
    }
});