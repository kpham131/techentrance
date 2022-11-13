var editButton = document.getElementById('editProfile')
var pathArray = window.location.pathname.split('/');
userId = pathArray[2]
var url = "http://localhost:8080/users/" + userId + "/editPersonalInfo"
console.log(url)
editButton.setAttribute('href', url)



var addSkillButton = document.getElementById('addSkills')
userId = pathArray[2]
var AddSkillsUrl = "http://localhost:8080/users/" + userId + "/skills"
console.log(AddSkillsUrl)
addSkillButton.setAttribute('href', AddSkillsUrl)