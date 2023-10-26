import Swal from "sweetalert2"

class SweetAlert{
    
    missingField(){
        Swal.fire({
            title : "Empty fields Detected",
            text : "Please Fill All the fields",
            icon : 'error'
        })
    }

    loginSuccessSwal(tag) {
        Swal.fire({
          position: 'center',
          allowOutsideClick: false,
          html: `<div class="animation">${tag}<span class="dot1">.</span><span class="dot2">.</span><span class="dot3">.!</span></div>`,
          showConfirmButton: false,
          background: "rgb(240, 240, 240)",
          timer: 1800,
          width: 500,
          timerProgressBar: true,
          backdrop: `linear-gradient(90deg, rgba(156,255,251,1) 0%, rgba(238,255,167,1) 35%, rgba(150,255,158,1) 100%)`,
          customClass: {
            timerProgressBar: "custom-swal-timer",
          },
        });
      };
      
      signOutSweetAlert(method, tag){
        Swal.fire({
            title : "Are you Sure you want to Sign out?",
            showConfirmButton : true,
            confirmButtonText : "Sign out",
            showCancelButton : true,
            cancelButtonText : "Stay In",
            customClass:{
                cancelButton : "custom-swal-button",
            },
            icon : "warning"
        }).then((result) => {
            if(result.isConfirmed){
                method();
                this.loginSuccessSwal(tag);
            } 
        })
    };

    successAlert(tag){
        Swal.fire({
            title : tag + " Successfully",
            icon : "success",
            timer : 1500
          })
    }

    alreadyExists(tag){
        Swal.fire({
            title : "" + tag + " already Exists",
            text : "Change the name of the " + tag ,
            icon : "warning"
        })
    }

    incorrectValues(){
        Swal.fire({
            title : "Incorrect Values Detected",
            text : "Enter Valid Numbers",
            icon : "error"
        })
    }

    deleteAlert(tag, id, aFunction){
        Swal.fire({
            title : "Delete " + tag + "?",
            text : "Are you sure You want to delete? It cannot be undone",
            icon : "warning",
            showCancelButton : true,
            cancelButtonText : "No",
            showConfirmButton : true,
            confirmButtonText : "Delete",
            customClass:{
                confirmButton : "custom-swal-button",
                cancelButton : "custom-swal-button"
            }
        }).then((result) => {
            if(result.isConfirmed){
                aFunction(id);
            }
        })
    }

    duplicateOptions(){
        Swal.fire({
            title : "Duplicate Options Found",
            icon : "error"
        })   
    }

    limitReached(){
        Swal.fire({
            title : "You have reached the limit",
            icon : 'warning',
            showConfirmButton: false,
            timer : 1500
        })
    }

    quizSubmitted(){
        Swal.fire({
            title : "Quiz submitted",
            text : "redirecting to Report",
            timer : 1500,
            timerProgressBar : true,
            showConfirmButton : false,
            backdrop: `rgba(80,108,62,0.7)`
        })
    }

    registrationSuccessFireAlert(img){
        Swal.fire({
          title : "Successfully Registered",
          text : "Hurray, Now you are our subscriber.!",
          imageUrl : img,
          imageHeight : 150,
          imageWidth : 150,
        })
      }
    
      registrationFailureFireAlert(failureText) {
        Swal.fire({
          title : "Unable to Register",
          text : failureText,
          icon : "error"
        })
      }

     alertError(message){
        Swal.fire({
            title : "Login Failed",
            text : message,
            icon : "error"
        })
    }

    redirecting(navigate){
        Swal.fire({
            html : `<div class="animation">Redirecting to Report<span class="dot1">.</span><span class="dot2">.</span><span class="dot3">.</span></div>`,
            timer : 2000,
            showConfirmButton : false,
            backdrop: `linear-gradient(90deg, rgba(156,255,251,1) 0%, rgba(238,255,167,1) 35%, rgba(150,255,158,1) 100%)`,
        }).then(() => {
            navigate();
        })
    }
}

export default new SweetAlert();