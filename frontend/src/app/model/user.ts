export interface User {
  id: number,
  username: string,
  roles: Array<string>,
  blocked: boolean,
  password: string
}