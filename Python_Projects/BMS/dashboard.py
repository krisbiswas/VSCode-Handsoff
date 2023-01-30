import tkinter as tk
import time

class Dashboard(tk.Frame):
    def __init__(self, parent, controller) -> None:
        tk.Frame.__init__(self, parent)
        self.controller = controller
        self.fetchUserData()
        self.recreate()

    def fetchUserData(self):
        self.username = "sdfgfsdf"
        self.address = "354d, ADsfpn, dsfkmdskl,fsdfkmsdm-131213, us"
        self.ID = 356412353435
        self.balance = 3000

    def recreate(self):
        tk.Label(self, text="Name",font=("times new roman",12,"bold")).grid(row=1,column=2)
        tk.Label(self, text=self.username,font=("times new roman",12)).grid(row=2,column=2)
        tk.Label(self, text="Address",font=("times new roman",12,"bold")).grid(row=3,column=2)
        tk.Label(self, text=self.address,font=("times new roman",12)).grid(row=4,column=2)
        tk.Label(self, text="ID",font=("times new roman",12,"bold")).grid(row=5,column=2)
        tk.Label(self, text=self.ID,font=("times new roman",12)).grid(row=6,column=2)
        tk.Label(self, text="Balance",font=("times new roman",12,"bold")).grid(row=7,column=2)
        tk.Label(self, text=self.balance,font=("times new roman",12)).grid(row=8,column=2)
        
    # def recreate(self):
    #     tk.Label(self, text="Name",font=("times new roman",12,"bold")).pack()
    #     tk.Label(self, text=self.username,font=("times new roman",12)).pack()
    #     tk.Label(self, text="Address",font=("times new roman",12,"bold")).pack()
    #     tk.Label(self, text=self.address,font=("times new roman",12)).pack()
    #     tk.Label(self, text="ID",font=("times new roman",12,"bold")).pack()
    #     tk.Label(self, text=self.ID,font=("times new roman",12)).pack()
    #     tk.Label(self, text="Balance",font=("times new roman",12,"bold")).pack()
    #     tk.Label(self, text=self.balance,font=("times new roman",12)).pack()