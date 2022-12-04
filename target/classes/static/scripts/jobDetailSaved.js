var profileDom = document.getElementById('unsaveJob')
var pathArray = window.location.pathname.split('/');
userId = pathArray[2]
jobId = pathArray[4]
var url = "http://localhost:8080/users/" + userId + "/jobs/" + jobId +"/unsave"
console.log(url)
profileDom.setAttribute('href', url)