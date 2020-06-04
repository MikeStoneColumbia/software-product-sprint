// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.


function responseNavBar(){

	var navBar = document.getElementById("myNavBar")
	
	console.log(navBar.className)
	console.log(navBar.className === "navBar")
	if(navBar.className === "navBar"){

		navBar.className += " responsive"
		console.log("made it into responsive")

	}else
		navBar.className = "navBar"

}

//TODO: Find out why the hover property disapears and fix it

function switchTab(tabName,element){ // the idea here is to make all other categories disapear besides the one just clicked

    var navContent = document.getElementsByClassName("navContent") // get the navContent divs
    var navTab = document.getElementsByClassName("navTab");

    for(var i = 0; i < navTab.length; i++){
        navTab[i].style.color = "rgb(247, 51, 67)";
        navTab[i].style.backgroundColor = "black";
        
        
    }    

    for(var i = 0; i < navContent.length; i++) // make them all disapear
        navContent[i].style.display = "none";
    


    document.getElementById(tabName).style.display = "block"; // display the appropiate tab info
    element.style.backgroundColor = "rgb(1, 153, 1)" // set the active tab to green
    element.style.color = "white"
    
}

document.getElementsByClassName("active")[0].click();
