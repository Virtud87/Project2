function login(data) {
  //   e.preventDefault();
  console.log(data);
  const errorEl = document.querySelector("#hideErrorMessage");

  // send data retrieved from database to localStorage
  localStorage.setItem("pseudoToken", JSON.stringify(data));

  // redirect user base the role property in data
  let expr = data.role;
  switch (expr) {
    case "business":
      location.href = "business.html";
      break;
    case "Shark":
      location.href = "shark.html";
      break;
    default:
      errorEl.setAttribute("id", "errorMessage");
      errorEl.textContent =
        "Opps, something went wrong...refresh and try again!";
  }
}

function checkFetch(response) {
  if (!response.ok) {
    throw Error(`${response.statusText} - ${response.url}`);
  }
  return response;
}

const getToken = () => {
  //   Retrieves the user token from localStorage
  return JSON.parse(localStorage.getItem("pseudoToken"));
};

async function loginUser2() {
  // error message selector
  const errorEl = document.querySelector("#hideErrorMessage");

  // Get username, password, and role from inputs
  let userName = document.querySelector("#userName").value.trim();
  let userPassword = document.querySelector("#password").value.trim();
  let role = document.querySelector("#roleSignIn").value;

  // let arrayData = [userName, userPassword, role];

  // create object for request body
  let loginData = {
    username: userName,
    password: userPassword,
  };

  let url = "http://localhost:8080/";

  //   get url depending the role the user selects
  role === "Business" ? (url += `business/login`) : (url += `shark/login`);

  // use fetch to send login request to server
  fetch(url, {
    method: "POST",
    mode: "cors",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
    body: JSON.stringify(loginData),
  })
    .then((response) => response.json())
    .then((data) => {
      login(data);
    })
    .catch((err) => {
      errorEl.setAttribute("id", "errorMessage");
      errorEl.textContent = err;
    });
}

const logout = () => {
  // delete fake token from local storage
  localStorage.removeItem("pseudoToken");

  // redirect user back to home page
  location.href = "home.html";
};

const createUser = (e) => {
  e.preventDefault();
  const signUpButtonEl = document.querySelector("#submitSignUpButton");

  // error message selector
  const errorEl = document.querySelector("#hideErrorMessage");
  let role = document.querySelector("#roleSignIn").value.trim();
  let data = signupValidation();

  if (data === "undefined") {
    errorEl.style.display = "block";
    signUpButtonEl.disabled = true;
    return;
  }

  console.log(data);
  let url = "http://localhost:8080/";
  role === "Business" ? (url += `business/create`) : (url += `shark`);

  // use fetch to send sign up request to server
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
    .then((location.href = "home.html"))
    .catch((err) => {
      errorEl.style.display = "block";
      console.log(err);
    });
};

// ----------------- SIGNUP VALIDATION-------------------

function signupValidation() {
  let firstName = document.querySelector("#firstName").value.trim();
  let lastName = document.querySelector("#lastName").value.trim();
  let businessName = document.querySelector("#businessName").value.trim();
  let username = document.querySelector("#userName").value.trim();
  let password = document.querySelector("#signupPassword").value.trim();
  let role = document.querySelector("#roleSignIn").value.trim();
  let invalidChars = [
    "0",
    "1",
    "2",
    "3",
    "4",
    "5",
    "6",
    "7",
    "8",
    "9",
    "!",
    "@",
    "#",
    "$",
    "%",
    "^",
    "&",
    "*",
  ];

  let data = {};

  if (
    firstName.length == 0 ||
    lastName.length == 0 ||
    businessName.length == 0 ||
    username.length == 0 ||
    password.length == 0
    // role != "Shark" ||
    // role != "Business"
  ) {
    alert("Please enter all values!");
    return;
  } else if (
    firstName.length > 20 ||
    lastName.length > 20 ||
    businessName.length > 20 ||
    username.length > 20 ||
    password.length > 30
  ) {
    alert("Your entry is too long!");
  } else {
    data.firstName = firstName;
    data.lastName = lastName;
    data.businessName = businessName;
    data.username = username;
    data.password = password;
    data.role = role;
  }

  if (username.length < 5) {
    alert("Your username is too short!");
  } else {
    data.username = username;
  }

  if (password.length < 8) {
    alert("Your password is too short!");
  } else {
    data.password = password;
  }

  for (char of invalidChars) {
    if (
      firstName.includes(char) ||
      lastName.includes(char) ||
      role.includes(char)
    ) {
      alert("Please enter only letters!");
      break;
    } else {
      data.firstName = firstName;
      data.lastName = lastName;
      data.role = role;
    }
  }
  return data;
}