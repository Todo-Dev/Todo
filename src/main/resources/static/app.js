


//add tasks

//$(document).ready(function () {
//    $('add').click(function () {
//        $('#to-do').append("<ul>" + $("input[name=taskText]").val() + " <a href='#' class='close' aria-hidden='true'>&times;</a></ul>");
//    });
//    $("body").on('click', '#to-do a', function () {
//        $(this).closest("ul").remove();
//    });
//});
function addTask() {
  /* Get task text from input */
  var inputTask = document.getElementById("taskText").value;
  /* Add task to the 'To Do' column */
  document.getElementById("to-do").innerHTML +=
    "<li class='task'><p>" + inputTask + "</p></li>";
  /* Clear task text from input after adding task */
  document.getElementById("taskText").value = "";
}








