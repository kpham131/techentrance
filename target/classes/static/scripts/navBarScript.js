var profileDom = document.getElementById('profile')
var pathArray = window.location.pathname.split('/');
userId = pathArray[2]
var url = "http://localhost:8080/users/" + userId + "/profile"
console.log(url)
profileDom.setAttribute('href', url)

var savedJobs = document.getElementById('savedJobs')
var savedJobsUrl = "http://localhost:8080/users/" + userId + "/savedJobs"
savedJobs.setAttribute('href', savedJobsUrl)

var about = document.getElementById('about')
var aboutUrl = "http://localhost:8080/users/" + userId + "/about"
about.setAttribute('href', aboutUrl)