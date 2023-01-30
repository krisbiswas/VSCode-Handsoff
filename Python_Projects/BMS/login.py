import tkinter as tk
from dashboard import Dashboard

class Register(tk.Frame):
    def __init__(self, parent, controller) -> None:
        tk.Frame.__init__(self, parent)
        self.controller = controller
        self.createUI()

    def createUI(self):
        tk.Label(self, text="Email", font=("times new roman",12,"bold")).grid(row=1,column=1)
        self.uname = tk.StringVar()
        tk.Entry(self, textvariable=self.uname, font=("times new roman",12)).grid(row=1,column=3)

        tk.Label(self, text="Password",font=("times new roman",12,"bold")).grid(row=3,column=1)
        self.pswd = tk.StringVar()
        tk.Entry(self, textvariable=self.pswd, show="*", font=("times new roman",12)).grid(row=3,column=3)
        tk.Button(self, text="Register", borderwidth=2, command=self.signup).grid(row=5,column=2)

    def signup(self):
        # Create user in DB
        # switch 'back' to login page
        pass

class Login(tk.Frame):
    def __init__(self, parent, controller) -> None:
        tk.Frame.__init__(self, parent)
        self.controller = controller
        self.createUI()

    def createUI(self):
        tk.Label(self, text="Email", font=("times new roman",12,"bold")).grid(row=1,column=2)
        self.uname = tk.StringVar()
        tk.Entry(self, textvariable=self.uname, font=("times new roman",12)).grid(row=2,column=2)

        tk.Label(self, text="Password",font=("times new roman",12,"bold")).grid(row=3,column=2)
        self.pswd = tk.StringVar()
        tk.Entry(self, textvariable=self.pswd, show="*", font=("times new roman",12)).grid(row=4,column=2)
        tk.Button(self, text="Sign In", borderwidth=2, command=self.login).grid(row=5,column=2)
        tk.Button(self, text="Register", borderwidth=2, command=self.register).grid(row=6,column=2)

    def check_user(self, uname, pswd)->bool:
        userPswd = self.controller.userCreds.get(uname.get())
        if userPswd != None and pswd.get() == userPswd:
            return True
        else:
            return False
        
    def login(self)->None:
        print(self.uname.get())
        if self.check_user(self.uname, self.pswd):
            self.controller.backStack.append(self.__class__)
            self.controller.show_frame(Dashboard)
        else:
            print("Invalid username or password!")
        

    def register(self)->None:
        self.controller.backStack.append(self.__class__)
        self.controller.show_frame(Register)
