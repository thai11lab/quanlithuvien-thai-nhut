$(".submit-btn").click(function (e) {
    e.preventDefault();
    var code = $("#code").val();
    var name = $("#name").val();
    var totalBook = $("#totalBook").val();
    var company = $("#company").val();

    if (code !== "" && name !== "" && totalBook !== "" && company !== "" && parseInt(totalBook) > 1) {
        $("#form-add").submit();
    }

    if (code == "") {

        $("#validate-code").html("Vui không để trống");
    } else {
        $("#validate-code").html("");
    }
    if (name == "") {
        $("#validate-name").html("Vui không để trống");
    } else {
        $("#validate-name").html("");
    }

    if (totalBook == "") {
        $("#validate-number").html("Vui không để trống");
    } else if (parseInt(totalBook) < 1) {
        $("#validate-number").html("Số lượng sách phải lớn hơn 0");
    } else {
        $("#validate-number").html("");
    }

    if (company == "") {
        $("#validate-company").html("Vui không để trống");
    } else {
        $("#validate-company").html("");
    }

});