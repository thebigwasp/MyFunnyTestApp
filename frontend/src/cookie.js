export default function getCookie(cookieName) {
  cookieName = cookieName + '=';
  for (let cookie of document.cookie.split(';')) {
    cookie = cookie.trim();
    if (cookie.startsWith(cookieName)) {
      return cookie.substring(cookieName.length, cookie.length);
    }
  }

  return null;
}
