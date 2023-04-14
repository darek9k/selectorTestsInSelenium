
     const myFunctionAxios = () => {
        
        console.log(axios)

        let axiosConfig = {
            headers: {
                'Content-Type': 'application/json;charset=UTF-8',
                "Access-Control-Allow-Origin": "*",
            }
          };
          var form = document.getElementById('myForm');
          console.log(form)
          var formData = new FormData(form);
          let data =  JSON.stringify(Object.fromEntries(formData));
          console.log(data);
    

        axios.post('http://192.168.0.14:8080/api/bmi', data , axiosConfig)
            .then(function (response) {
                console.log(response.data);
                document.getElementById('bmi').innerText=response.data.bmi;
                document.getElementById('bmiNote').innerText=response.data.ocenaWagi;
            })
            .catch(function (error) {
                console.log(error);
                document.getElementById('bmi').innerText='';
                document.getElementById('bmiNote').innerText='';
                document.getElementById('errorMsg').innerText='Niepoprawna waga lub wzrost';
            });
    }
    
    const myFunctionLocal = () => {
        
        console.log(axios)

        let axiosConfig = {
            headers: {
                'Content-Type': 'application/json;charset=UTF-8',
                "Access-Control-Allow-Origin": "*",
            }
          };
          
          try{
            let weight = parseFloat(document.getElementById('waga').value);
            let height = parseFloat(document.getElementById('wzrost').value)/100;
            console.log(weight);  
            console.log(height);  

            let bmi = (weight/height/height).toFixed(2);
            let bmiNote = bmi < 20 ? 'NIEDOWAGA' : bmi > 25 ? 'NADWAGA' : 'OK';

            console.log("Bmi" + bmi);  
            document.getElementById('bmi').innerText=bmi;
            document.getElementById('bmiNote').innerText=bmiNote;
          }catch(error){
            console.error(error);
            document.getElementById('bmi').innerText='';
            document.getElementById('bmiNote').innerText='';
            document.getElementById('errorMsg').innerText='Niepoprawna waga lub wzrost';
          }
    }