
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

          
          try{
            if(document.getElementById('bmi')) document.getElementById('bmi').id='bmiTmp';
            if(document.getElementById('bmiNote')) document.getElementById('bmiNote').id='bmiNoteTmp';
            let weight = parseFloat(document.getElementById('waga').value);
            let height = parseFloat(document.getElementById('wzrost').value)/100;
            console.log(weight);  
            console.log(height);  
            
            let bmi = (weight/height/height).toFixed(2);
            let bmiNote = bmi < 20 ? 'NIEDOWAGA' : bmi > 25 ? 'NADWAGA' : 'OK';
            
            let sleepTimeMs = 2500;
            console.log("Bmi: " + bmi); 
            console.log("Czekam "+ sleepTimeMs + "ms ... ");  
            sleep(sleepTimeMs).then(
              ()=>{
                console.log("Ustawiam bmi");
                
                document.getElementById('bmiTmp').innerText=bmi;
                document.getElementById('bmiTmp').id='bmi';
                console.log("Ustawiłem bmi");
                console.log("Czekam "+ sleepTimeMs + "ms ... ");  
                sleep(sleepTimeMs).then(
                  ()=>{
                    
                  document.getElementById('bmiNoteTmp').innerText=bmiNote;
                  document.getElementById('bmiNoteTmp').id='bmiNote';
                  console.log("Ustawiłem bmiNote");
                }
                ) ;

                
              }
            ) ;
            
          }catch(error){
            console.error(error);
            document.getElementById('bm').innerText='';
            document.getElementById('bmiNote').innerText='';
            document.getElementById('errorMsg').innerText='Niepoprawna waga lub wzrost';
          }
    }

    const sleep = (ms) => {
      return new Promise(resolve => setTimeout(resolve, ms));
    }