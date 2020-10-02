


window.onload = function(){

};

function doDoubleclick(){
   let body = document.getElementsByTagName("body")[0];
   if(body.style.backgroundColor=="rgb(206, 237, 214)"){
        body.style.backgroundColor="rgb(255, 225, 225)";
   }else{
        body.style.backgroundColor="rgb(206, 237, 214)";
   }

}