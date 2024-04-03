$(function(){
    $('table').DataTable();
})

let form = document.getElementById("myform");
   form.addEventListener("submit", function(e){
	   let matricule = document.getElementById("matricule");
	   let nom = document.getElementById("nom");
	   let prenom = document.getElementById("prenom");
	   let numero = document.getElementById("numero");
	   let date = document.getElementById("date");
	   let form = document.getElementById("myform");
	   if(matricule.value==""){
		   e.preventDefault()

           Swal.fire({
            icon: 'error',
            title: 'il faut remplir ',
            text: 'tous le formulaire !',
          })


		   let erreur = document.getElementById("erreur");
		   erreur.innerHTML="vous avez laissez champs vide";
		   erreur.style.color="red";
	   }
	   if(nom.value==""){
		   e.preventDefault()
           Swal.fire({
            icon: 'error',
            title: 'il faut remplir ',
            text: 'tous le formulaire !',
          })
		   let erreur = document.getElementById("erreurNom");
		   erreur.innerHTML="vous avez laissez champs vide";
		   erreur.style.color="red";
	   }
	   if(prenom.value==""){
		   e.preventDefault()
		   let erreur = document.getElementById("erreurPrenom");
		   erreur.innerHTML="vous avez laissez champs vide";
		   erreur.style.color="red";
           Swal.fire({
            icon: 'error',
            title: 'il faut remplir ',
            text: 'tous le formulaire !',
          })
	   }
	   if(numero.value==""){
		   e.preventDefault()
           Swal.fire({
            icon: 'error',
            title: 'il faut remplir ',
            text: 'tous le formulaire !',
          })
		   let erreur = document.getElementById("erreurNumero");
		   erreur.innerHTML="vous avez laissez champs vide";
		   erreur.style.color="red";
	   }
	   if(date.value==""){
		   e.preventDefault()
           Swal.fire({
            icon: 'error',
            title: 'il faut remplir ',
            text: 'tous le formulaire !',
          })
		   let erreur = document.getElementById("erreurDate");
		   erreur.innerHTML="vous avez laissez champs vide";
		   erreur.style.color="red";
	   }
       else{
        Swal.fire({
            position: 'top-end',
            icon: 'success',
            title: 'ajouter avec succes',
          })
       }
   })
   

   