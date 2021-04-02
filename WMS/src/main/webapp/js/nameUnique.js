const name = $("#name");
const show = $("#nameShow");
let isTrueName = false;
name.on("blur", function () {
    var nameTxt=$("#name").val();
    if (nameTxt==null||nameTxt===''){
        show.html("物品名不能为空");
        isTrueName=false;
    }
    $.ajax({
        type:'GET',
        url:"UniqueWareName.do",
        data:{name:nameTxt},
        dataType:"json",
        success:function (date) {
            switch (date.result){
                case "t":
                    show.html("物品名不存在");
                    isTrueName=false;
                    break;
                case "f":
                    show.html("");
                    isTrueName=true;
                    break;
                default:
                    show.html("出现奇怪的错误请与管理员联系！");
                    isTrueName = false;
                    break;
            }
        },
        error: function () {
            show.html("请求出错！");
            isTrueName = false;
        }
    })
})
function onClickSubmit(){
    return isTrueName;
}