document.getElementById("submitwithdrawalButton").onclick = function() {withdrawal()};

async function withdrawal() {
	var root = ""
		
	var memberId = document.getElementById("memberId").value
	var memberPassword = document.getElementById("memberPassword").value
	
    if (confirm("계정을 삭제하시겠습니까?")) {
      let config = {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
      };
      await fetch(`${root}/mypage/withdrawal/${memberId}/${memberPassword}`, config);
      window.location.replace("/");
    }
}