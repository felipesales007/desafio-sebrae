export class UserRequest {
  "id": Number
  "name": string
  "password": string
  "confirmPassword": string
  "email": string
  "telefone": Number

  constructor() {
    this.id = null
    this.name = null
    this.password = null
    this.email = null
    this.telefone = null
    this.confirmPassword = null
  }

}
