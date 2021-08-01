
alert("hello")
function addTask() {
  /* Get task text from input */
  var inputTask = document.getElementById("taskText").value;
  // Add task to the 'To Do' column /
  document.getElementById("to-do").innerHTML +=
    "<li class='task'><p>" + inputTask + "</p></li>";
  /* Clear task text from input after adding task */
  document.getElementById("taskText").value = "";
}