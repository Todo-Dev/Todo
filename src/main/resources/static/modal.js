var myModal = document.getElementById('myModal')
var myInput = document.getElementById('myInput')

myModal.addEventListener('shown.bs.modal', function () {
  myInput.focus()
})



//document.getElementById('updateSectionForm').addEventListener('submit', (e) => {
//  const formData = new FormData(e.target);
//  // Now you can use formData.get('foo'), for example.
//  let title = formData.get(title);
//  // Don't forget e.preventDefault() if you want to stop normal form .submission
//});