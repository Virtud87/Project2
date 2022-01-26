// Logout, employee name and form selectors
const sharkLogoutButton = document.querySelector("#sharkLogoutEl");
const nameEl = document.querySelector("#headerFullName");
const profileNameEl = document.querySelector("#fullName");
const businessNameEl = document.querySelector("#businessName");
const sendCommentShark = document.querySelector("#sendButtonShark");
const pitchModalButton = document.querySelector("#pitchesDiv");
const sendPitchOffer = document.querySelector("#submitPitchButton");

// Add name to banner and profile
nameEl.textContent = getName();
profileNameEl.textContent = getName();

// Add business name to element
businessNameEl.textContent = getBusinessName();

// get and render pitches
let pitchesSharkUrl = `http://localhost:8080/pitches`;
fetch(pitchesSharkUrl)
  .then((res) => res.json())
  .then((data) => renderPitches(data))
  .catch((err) => console.log(err));

// get and render comments
let commentsSharkUrl = `http://localhost:8080/commentingAll`;
fetch(commentsSharkUrl)
  .then((res) => res.json())
  .then((data) => createComment(data))
  .catch((err) => console.log(err));

// Logout event listener
sharkLogoutButton.addEventListener("click", logout);

// send comment event listener
sendCommentShark.addEventListener("click", sendSharkComment);

// shark offer event listener
sendPitchOffer.addEventListener("click", function () {
  let errorEl = document.querySelector("#commentError");
  let pitchData = offerSharkValidation();

  if (pitchData === undefined) {
    errorEl.style.display = "block";
    return;
  } else {
    makeOfferShark(pitchData);
  }
});

pitchModalButton.addEventListener("click", pitchIdShark);