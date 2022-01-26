// function for getting name from local storage
const getName = () => {
  let tokenData = getToken();
  return `${tokenData["firstName"]} ${tokenData["lastName"]}`;
};

const getBusinessId = () => {
  let tokenData = getToken();
  return tokenData.businessId;
};

const getBusinessName = () => {
  let tokenData = getToken();
  return tokenData.businessName;
};

const getSharkId = () => {
  let tokenData = getToken();
  return tokenData.sharkId;
};

function checkFetch(response) {
  if (!response.ok) {
    throw Error(`${response.statusText} - ${response.url}`);
  }
  return response;
}

// function for sending a comment from the shark end
const sendSharkComment = (e) => {
  e.preventDefault();

  let successEl = document.querySelector("#commentSent");
  let errorEl = document.querySelector("#commentSharkError");

  let data = commentSharkValidation();

  // url
  let url = "http://localhost:8080/commenting/create";

  fetch(url, {
    method: "POST",
    mode: "cors",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data),
  })
    .then(checkFetch)
    .then((res) => {
      return res.json();
    })
    .then((data) => {
      console.log(data);
      successEl.style.display = "block";
    })
    .catch((err) => {
      errorEl.style.display = "block";
      console.log(err);
    });
};

// function for sending a comment from the business end
const sendBusinessComment = (e) => {
  e.preventDefault();

  let errorEl = document.querySelector("#commentError");
  let successEl = document.querySelector("#commentSent");

  let reciepient = document.querySelector("#reciepientfromBiz").value.trim();
  let date = document.querySelector("#commentDateBiz").value.trim();
  let comment = document.querySelector("#commentBusiness").value.trim();
  let bizId = JSON.parse(localStorage.getItem("pseudoToken"));

  let data = commentValidation();

  // url
  let url = "http://localhost:8080/commenting/create";

  fetch(url, {
    method: "POST",
    mode: "cors",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data),
  })
    .then(checkFetch)
    .then((res) => {
      return res.json();
    })
    .then((data) => {
      console.log(data);
      successEl.style.display = "block";
    })
    .catch((err) => {
      errorEl.style.display = "block";
      console.log(err);
    });
};

// function for creating pitch
const createPitchBusiness = (e) => {
  e.preventDefault();

  let errorPitchEl = document.querySelector("#pitchCommentError");
  let successPitchEl = document.querySelector("#pitchCreatedSuccess");

  let url = "http://localhost:8080/pitch";

  let data = pitchCreateBusinessValidation();

  if (
    data.businessId === undefined ||
    data.businessName === undefined ||
    data.creationDate === undefined ||
    data.pitch === undefined ||
    data.amount === undefined ||
    data.percentage === undefined
  ) {
    errorPitchEl.style.display = "block";
    return;
  } else {
    fetch(url, {
      method: "POST",
      mode: "cors",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      body: JSON.stringify(data),
    })
      .then(checkFetch)
      .then((response) => response.json())
      .then((data) => {
        successPitchEl.style.display = "block";
        console.log(data);
      })
      .catch((err) => {
        errorPitchEl.style.display = "block";
        console.log(err);
      });
  }
};

const pitchIdShark = (e) => {
  localStorage.setItem("pitchId", e.target.dataset.pitchid);
};

// function for accepting pitch (shark)
const makeOfferShark = (data) => {

  // success and error p tags
  let successEl = document.querySelector("#pitchSent");
  let errorEl = document.querySelector("#commentError");

  // get urls
  let url = "http://localhost:8080/offer";

  // fetch with patch method, success then, failure catch
  fetch(url, {
    method: "PATCH",
    mode: "cors",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data),
  })
    .then(checkFetch)
    .then((res) => {
      return res.json();
    })
    .then((data) => {
      successEl.style.display = "block";
    })
    .catch((err) => {
      errorEl.style.display = "block";
      console.log(err);
    });
};

// function for accepting pitch (business)
const acceptPitchBusiness = (e) => {
  e.preventDefault();

  // get id
  let id = document.querySelector("#businessPitchButton");
  let pitchId = id.dataset.pitchid;
  let data = { pitchId: parseInt(pitchId) };

  // get urls
  let url = "http://localhost:8080/";

  // fetch with patch method, success then, failure catch
  fetch(url + `accept/${pitchId}`, {
    method: "PATCH",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data),
  })
    .then(checkFetch)
    .then((res) => {
      return res.json();
    })
    .then((data) => {
      alert("Accepted");
    })
    .catch((err) => {
      // errorEl.style.display = "block";
      console.log(err);
    });
};

// dynamically rendered pitches
const renderPitches = (data) => {
  // div that holds all pitches
  const commentsDivEl = document.querySelector("#pitchesDiv");

  data.forEach((c) => {
    // div that holds pitch
    let createDivTag = document.createElement("div");
    createDivTag.setAttribute("id", "pitchDiv");

    // added img for business logo
    let createImgTag = document.createElement("img");
    createImgTag.setAttribute("id", "bizPic");
    createImgTag.setAttribute("src", "./assets/images/defaultLogo.png");
    createImgTag.setAttribute("alt", "business");

    // div that holds namePercentAmountDiv & textDiv
    let createBizInfoDivTag = document.createElement("div");
    createBizInfoDivTag.setAttribute("id", "bizInfoDiv");

    // div that holds business name, percent, pitchAmount, accepted, button
    let createNamePercentAmountDivTag = document.createElement("div");
    createNamePercentAmountDivTag.setAttribute("id", "namePercentAmountDiv");

    // p tags for bizName, percent, pitchAmount, pitchAmount, pitchButtonShark
    let createbizNameP = document.createElement("p");
    createbizNameP.setAttribute("id", "bizName");
    createbizNameP.textContent = c.businessName;

    let createPercentP = document.createElement("p");
    createPercentP.setAttribute("id", "percent");
    let decimal = c.percentage;
    createPercentP.textContent = `${decimal.toFixed(2)}%`;

    let createPitchAmountP = document.createElement("p");
    createPitchAmountP.setAttribute("id", "pitchAmount");
    createPitchAmountP.textContent = `$${c.amount
      .toString()
      .replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1,")}`;

    let createPitchAcceptedP = document.createElement("p");
    createPitchAcceptedP.setAttribute("id", "pitchAccepted");

    if (c.status === "Accepted") {
      createPitchAcceptedP.textContent = c.status;
      createPitchAcceptedP.style.display = "block";
    }

    let createButton = document.createElement("button");
    createButton.setAttribute("id", "pitchButtonShark");
    createButton.setAttribute("type", "button");
    createButton.setAttribute("id", "pitchButtonShark");
    createButton.setAttribute("class", "btn btn-primary");
    createButton.setAttribute("data-bs-toggle", "modal");
    createButton.setAttribute("data-bs-target", "#pitchModalShark");
    createButton.setAttribute("data-pitchId", c.pitchId);
    createButton.setAttribute("data-businessId", c.businessId);
    createButton.textContent = "+";

    //div that holds pitchText
    let createPitchDivTag = document.createElement("div");
    createPitchDivTag.setAttribute("id", "textDiv");

    let createPitchTextP = document.createElement("p");
    createPitchTextP.setAttribute("id", "pitchText");
    createPitchTextP.textContent = c.pitch;

    createPitchDivTag.appendChild(createPitchTextP);
    createNamePercentAmountDivTag.append(
      createbizNameP,
      createPercentP,
      createPitchAmountP,
      createPitchAcceptedP,
      createButton
    );
    createBizInfoDivTag.append(
      createNamePercentAmountDivTag,
      createPitchDivTag
    );
    createDivTag.append(createImgTag, createBizInfoDivTag);
    commentsDivEl.appendChild(createDivTag);
  });
};

// dynamically rendered business pitches
const renderPitchBusiness = (data) => {
  let loggedInBusinessId = getBusinessId();

  // div that holds all pitches
  const commentsDivEl = document.querySelector("#pitchesDiv");

  data.forEach((c) => {
    if (c.businessId === loggedInBusinessId) {
      // div that holds pitch
      let createDivTag = document.createElement("div");
      createDivTag.setAttribute("id", "pitchDiv");

      // added img for business logo
      let createImgTag = document.createElement("img");
      createImgTag.setAttribute("id", "bizPic");
      createImgTag.setAttribute("src", "./assets/images/defaultLogo.png");
      createImgTag.setAttribute("alt", "business");

      // div that holds namePercentAmountDiv & textDiv
      let createBizInfoDivTag = document.createElement("div");
      createBizInfoDivTag.setAttribute("id", "bizInfoDiv");

      // div that holds business name, percent, pitchAmount, accepted, button
      let createNamePercentAmountDivTag = document.createElement("div");
      createNamePercentAmountDivTag.setAttribute("id", "namePercentAmountDiv");

      // p tags for bizName, percent, pitchAmount, pitchAmount, pitchButtonShark
      let createbizNameP = document.createElement("p");
      createbizNameP.setAttribute("id", "bizName");
      createbizNameP.textContent = c.businessName;

      let createPercentP = document.createElement("p");
      createPercentP.setAttribute("id", "percent");
      let decimal = c.percentage;
      createPercentP.textContent = `${decimal.toFixed(2)}%`;

      let createPitchAmountP = document.createElement("p");
      createPitchAmountP.setAttribute("id", "pitchAmount");
      createPitchAmountP.textContent = `$${c.amount
        .toString()
        .replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1,")}`;

      let createPitchAcceptedP = document.createElement("p");
      createPitchAcceptedP.setAttribute("id", "pitchAccepted");

      if (c.status === "Accepted") {
        createPitchAcceptedP.textContent = c.status;
        createPitchAcceptedP.style.display = "block";
      }

      let createButton = document.createElement("button");
      createButton.setAttribute("id", "businessPitchButton");
      createButton.setAttribute("type", "button");
      createButton.setAttribute("class", "btn btn-primary");
      createButton.setAttribute("data-bs-toggle", "modal");
      createButton.setAttribute("data-bs-target", "#acceptPitchModal");
      createButton.setAttribute("data-pitchId", c.pitchId);
      createButton.setAttribute("data-businessId", c.businessId);
      createButton.innerHTML = '<i class="fas fa-check"></i>';

      //div that holds pitchText
      let createPitchDivTag = document.createElement("div");
      createPitchDivTag.setAttribute("id", "textDiv");

      let createPitchTextP = document.createElement("p");
      createPitchTextP.setAttribute("id", "pitchText");
      createPitchTextP.textContent = c.pitch;

      createPitchDivTag.appendChild(createPitchTextP);
      createNamePercentAmountDivTag.append(
        createbizNameP,
        createPercentP,
        createPitchAmountP,
        createPitchAcceptedP,
        createButton
      );
      createBizInfoDivTag.append(
        createNamePercentAmountDivTag,
        createPitchDivTag
      );
      createDivTag.append(createImgTag, createBizInfoDivTag);
      commentsDivEl.appendChild(createDivTag);
    }
  });
};

// dynamic comments
const createComment = (data) => {
  // div that holds all comments
  const commentsDivEl = document.querySelector("#commentsDiv");

  data.forEach((c) => {
    // div that holds date and comment
    let createDivTag = document.createElement("div");
    createDivTag.setAttribute("id", "commentDiv");

    let createDateP = document.createElement("p");
    createDateP.setAttribute("id", "dateP");
    createDateP.textContent = c.createDate;

    let createCommentP = document.createElement("p");
    createCommentP.setAttribute("id", "comment");
    createCommentP.textContent = c.commenting;

    createDivTag.append(createDateP, createCommentP);
    commentsDivEl.appendChild(createDivTag);
  });
};

// -------------------SHARK MAKE COMMENT VALIDATION---------------

function commentSharkValidation() {
  let reciepient = document.querySelector("#reciepientfromShark").value.trim();
  let date = document.querySelector("#commentDateShark").value.trim();
  let comment = document.querySelector("#commentShark").value.trim();
  let sharkId = JSON.parse(localStorage.getItem("pseudoToken"));
  let data = {
    sharkId: sharkId.businessId,
  };

  if (reciepient === "Choose...") {
    alert("Please choose one of the available options.");
    return;
  } else {
    data.businessId = parseInt(reciepient);
  }

  if (date === "") {
    alert("Please select a date.");
    return;
  } else {
    data.createDate = date;
  }

  if (comment.length == 0) {
    alert("Please enter a comment.");
    return;
  } else {
    data.commenting = comment;
  }

  return data;
}

// -------------------Business MAKE COMMENT VALIDATION---------------

function commentBusinessValidation() {
  let dropdown = document.querySelector("#reciepientfromBiz");
  let commentSection = document.querySelector("#commentBusiness");
  let data = {};

  if (dropdown == null) {
    alert("Please choose one of the available options.");
  } else {
    data.dropdown = dropdown;
  }
  if (commentSection.length == 0) {
    alert("Please enter a comment.");
  } else {
    data.commentSection = commentSection;
  }
}

function offerSharkValidation() {
  // get id, amount, percentage
  let id = parseInt(localStorage.getItem("pitchId"));
  let amount = document.querySelector("#validationCustom02").value.trim();
  let percent = document.querySelector("#validationCustom03").value.trim();

  // error p tags
  let errorAmountEl = document.querySelector("#invalid-feedback-amount");

  let errorPercentEl = document.querySelector("#invalid-feedback-percent");

  let data = {};

  if (amount === "") {
    console.log(amount);
    errorAmountEl.style.dislay = "block";
    alert("Please enter a valid amount");
    return;
  } else {
    data.amount = amount;
  }

  if (percent === "") {
    console.log(percent);
    alert("Please enter a valid percent");
    errorPercentEl.style.dislay = "block";
    return;
  } else {
    data.percentage = parseFloat(percent);
  }

  data.pitchId = id;
  console.table(data);

  return data;
}

function pitchCreateBusinessValidation() {
  let errorPitchEl = document.querySelector(".invalid-feedback");

  let date = document.querySelector("#validationCustom01").value.trim();
  let amount = document.querySelector("#validationCustom02").value.trim();
  let percent = document.querySelector("#validationCustom03").value.trim();
  let pitchText = document.querySelector("#validationTextarea").value.trim();
  let businessId = getBusinessId();
  let businessName = getBusinessName();
  let data = {};

  if (date === "") {
    alert("Please enter a date");
    errorPitchEl.style.dislay = "block";
  } else {
    data.creationDate = date;
  }

  if (amount === "") {
    alert("Please enter an amount");
    errorPitchEl.style.dislay = "block";
  } else {
    data.amount = parseFloat(amount);
  }

  if (percent === "") {
    alert("Please enter an percent");
    errorPitchEl.style.dislay = "block";
  } else {
    data.percentage = parseFloat(percent);
  }

  if (pitchText === "") {
    alert("Please enter a pitch");
    errorPitchEl.style.dislay = "block";
  } else {
    data.pitch = pitchText;
  }

  data.businessId = businessId;
  data.businessName = businessName;

  console.table(data);

  return data;
}