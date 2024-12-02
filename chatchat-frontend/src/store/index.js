import {defineStore} from "pinia";
export const userStore=defineStore('general',{
    state: () => {
        return {
            user: {
                id: '',
                username: '',
                role: '',
                avatar: '',
                registerTime: ''
            },
        }
    }
});