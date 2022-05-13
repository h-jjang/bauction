export const ACCESS_TOKEN = "accessToken";
export const API_BASE_URL = `${process.env.REACT_APP_HOST}`;
export const OAUTH2_REDIRECT_URI = `${API_BASE_URL}/oauth2/redirect`;
export const SERVER_BASE_URL = `${process.env.REACT_APP_SERVER_HOST}`;
export const KAKAO_AUTH_URL = `${SERVER_BASE_URL}/oauth2/authorization/kakao?redirect_uri=${OAUTH2_REDIRECT_URI}`;