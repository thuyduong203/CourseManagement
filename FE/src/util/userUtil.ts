import { ref } from "vue";

interface User {
  id: number;
  code: string;
  name: string;
  birthDate: string;
  email: string;
  phone: string;
  gender: string;
  address: string;
  status: string;
  role: string;
}

const user = ref<User | null>(null);

function setUser(userLogin: User) {
  user.value = userLogin;
}

function getUser() {
  return user.value;
}

export { setUser, getUser };
