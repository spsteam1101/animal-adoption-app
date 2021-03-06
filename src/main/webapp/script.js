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

/**
 * Adds a random greeting to the page.
 */
function getDataServer(text)  {
    fetch('/animal-data').then(response => response.json()).then((posts) => {

    const statsListElement = document.getElementById('data-container');
    statsListElement.innerHTML = '';
    posts.forEach((posting) => {
    if (text === posting.page)  {
      statsListElement.appendChild(createListElement('Name: ' + posting.name));
      statsListElement.appendChild(createListElement('Post: ' + posting.post));
    }
    })
  });
}

function createListElement(text) {
  const liElement = document.createElement('li');
  liElement.innerText = text;
  return liElement;
}    

function getContent()  {
        fetch('/content').then(response => response.json()).then((con) => {

    const statsListElement = document.getElementById('data-container');
    statsListElement.innerHTML = '';
        statsListElement.appendChild(
            createListElement(con));
  });
}


