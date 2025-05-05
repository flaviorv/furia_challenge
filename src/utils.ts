import { jwtDecode } from 'jwt-decode';

interface DecodedJWT {
  sub: string;
  roles: { authority: string }[];
  exp: number;
  iat: number;
}

type UserInfo = {
  username: string;
  role: string;
};

export function getInfoFromToken(): UserInfo | null {
  const token: string | null = localStorage.getItem('furia-jwt');
  if (!token) return null;

  try {
    const decoded: DecodedJWT = jwtDecode(token);
    return {
      username: decoded.sub,
      role: decoded.roles[0].authority,
    };
  } catch {
    return null;
  }
}

export enum ComponentType {
  Welcome = 'Welcome',
  Login = 'Login',
  Signup = 'Signup',
}
