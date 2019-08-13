function checkObjProp(userInfo, prop) {
  if (userInfo == null || typeof userInfo === 'undefined' || userInfo[prop] == null || typeof userInfo[prop] === 'undefined') {
    return false;
  }
  else return true;
}

let initChatopera = function(userInfo) {
  console.log('calling chatoperaInit: ', userInfo);
  chatoperaInit(userInfo);
};

let initUserInfo = function(user, company) {
  let userInfo = { name: user, company };
  console.log("userInfo: ", userInfo);
  window.userInfo = userInfo;
};

function zendeskInit() {
  let currUrl = window.location.href;
  console.log('currUrl: ', currUrl);
  let turl = new URL(currUrl);
  let username = turl.searchParams.get("username");
  let company = turl.searchParams.get("company");
  let userInfo = { username, company };
  console.log('userInfo: ', userInfo);
  if (!checkObjProp(userInfo, 'username')) {
    console.log(`userInfo not defined, chatopera not available`);
  }
  else {
   const { username, company } = userInfo;
   let useremail = `${username}@${company}.com`;
   console.log(`using identity ${username}:${company}(${useremail}) ...`);
//   initUserInfo(username, company)
let userInfo2 = {
  uid: 1024,
  username,
  cid: 2048,
  company_name: company,
  sid: 1,
  system_name: "Tr_Sys"
};
   initChatopera(userInfo2);
  }
}


