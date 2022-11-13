var formDom = document.getElementById('form')
var pathArray = window.location.pathname.split('/');
userId = pathArray[2]
var url = "http://localhost:8080/users/" + userId + "/personalInfo"
console.log(url)
formDom.setAttribute('action', url)

next.onclick = function () {

  formDom.submit()
  // fetch("http://localhost:8080/users/"+userId+"/personalInfo", {
  //   method: 'post',
  //   body: JSON.stringify(skillArray),
  //   headers: {
  //     'Accept': 'application/json',
  //     'Content-Type': 'application/json'
  //   }
  // }).then((response) => {
  //   return response.json()
  // }).then((res) => {
  //   if (res.status === 201) {
  //     console.log("Post successfully created!")
  //   }
  // }).catch((error) => {
  //   console.log(error)
  // })

}
