


////add tasks
//
//$(document).ready(function () {
//    $('add').click(function () {
//        $('#to-do').append("<ul>" + $("input[name=taskText]").val() + " <a href='#' class='close' aria-hidden='true'>&times;</a></ul>");
//    });
//    $("body").on('click', '#to-do a', function () {
//        $(this).closest("ul").remove();
//    });
//});
//function addTask() {
//  /* Get task text from input */
//  var inputTask = document.getElementById("taskText").value;
//  /* Add task to the 'To Do' column */
//  document.getElementById("to-do").innerHTML +=
//    "<li class='task'><p>" + inputTask + "</p></li>";
//  /* Clear task text from input after adding task */
//  document.getElementById("taskText").value = "";
//}



var myNodelist = document.getElementsByTagName("LI");
var i;
for (i = 0; i < myNodelist.length; i++) {
  var span = document.createElement("SPAN");
  var txt = document.createTextNode("\u00D7");
  span.className = "close";
  span.appendChild(txt);
  myNodelist[i].appendChild(span);
}

// Click on a close button to hide the current list item
var close = document.getElementsByClassName("close");
var i;
for (i = 0; i < close.length; i++) {
  close[i].onclick = function() {
    var div = this.parentElement;
    div.style.display = "none";
  }
}

// Add a "checked" symbol when clicking on a list item
var list = document.querySelector('ul');
list.addEventListener('click', function(ev) {
  if (ev.target.tagName === 'LI') {
    ev.target.classList.toggle('checked');
  }
}, false);

// Create a new list item when clicking on the "Add" button
function newElement() {
  var li = document.createElement("li");
  var inputValue = document.getElementById("myInput").value;
  var t = document.createTextNode(inputValue);
  li.appendChild(t);
  if (inputValue === '') {
    alert("You must write something!");
  } else {
    document.getElementById("myUL").appendChild(li);
  }
  document.getElementById("myInput").value = "";

  var span = document.createElement("SPAN");
  var txt = document.createTextNode("\u00D7");
  span.className = "close";
  span.appendChild(txt);
  li.appendChild(span);

  for (i = 0; i < close.length; i++) {
    close[i].onclick = function() {
      var div = this.parentElement;
      div.style.display = "none";
    }
  }
}





