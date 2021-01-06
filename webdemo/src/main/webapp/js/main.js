const select = document.querySelector('#changeAvatar');
const inputUpload = document.querySelector('#uploadAvatar');
const preview = document.querySelector('#preview');
const feedback = document.querySelector('#feedback');



select.addEventListener('change', cambiarImagen);
inputUpload.addEventListener('change', updateImagePreview);

function updateImagePreview() {
  while(preview.firstChild) {
      preview.removeChild(preview.firstChild);
    }
  const file = inputUpload.files[0];
  
  const img = document.createElement('img');
  img.src=URL.createObjectURL(file);
  img.width="200";
  img.height="200";
  preview.appendChild(img);
  img.onclick = borrarPreview;
  if (feedback.firstChild) {
  	var alertNode = feedback.firstChild.nextSibling;
  	var alert = new bootstrap.Alert(alertNode)
  	alert.close();
  }
  
  
}

function borrarPreview() {
  document.getElementById("uploadForm").reset();
  preview.removeChild(preview.firstChild);
}

function cambiarImagen() {
  document.getElementById("changeAvatarForm").submit();
}