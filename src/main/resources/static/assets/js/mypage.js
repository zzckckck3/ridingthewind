const columns = document.querySelectorAll(".card-list");

columns.forEach((column) => {
    new Sortable(column, {
        group: "shared",
        animation: 150,
        ghostClass: "blue-background-class"
    });
});

let root = "";

// 회원 목록.
fetch(`${root}/mypage/list`)
  .then((response) => response.json())
  .then((data) => makeList(data));

function makeList(users){
	  var tbody = ``;
	  users.forEach(function (user) {
		  tbody += `
			    		<div draggable="true" class="card col" style="width: 13rem; id="${user.contentId}">
				          <img src="${user.firstImage}" class="card-img-top" alt="areaImage" onerror=this.src="${root}/assets/img/noimg.jpg">
				          <div class="card-body">
				            <h6 class="card-title">${user.title}</h6>
				            <p class="card-text">${user.addr1}</p>
				            <a href="#" class="btn btn-primary" onclick="moveCenter(${user.latitude}, ${user.longitude});">위치</a>
				          	<div style="display: none" class="latitude">${user.latitude}</div>
				        	<div style="display: none" class="longitude">${user.longitude}</div>
				            <button class="btn btn-danger" onclick="tripDelete('${user.memberId}', ${user.contentId});">삭제</button>
				          </div>
				        </div>
					`;
	    });
	  document.querySelector("#card-list").innerHTML = tbody;
	  document.querySelector("#card-list-custom").innerHTML = " ";
}


function tripDelete(memberId, contentId) {
    if (confirm("여행지를 삭제하시겠습니까?")) {
      let config = {
        method: "DELETE",
        headers: {
          "Content-Type": "application/json",
        },
      };
      fetch(`${root}/mypage/delete/${memberId}/${contentId}`, config)
        .then((response) => response.json())
        .then((data) => makeList(data));
    }
}


