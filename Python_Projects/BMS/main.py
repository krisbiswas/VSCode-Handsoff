from tkinter import*
from login import *
from dashboard import Dashboard

class App(Tk):

    userCreds = {"kris":"123456798","biswa":"22222222"}
    backStack = []

    def __init__(self, *args, **kwargs):
        Tk.__init__(self, *args, **kwargs)
        self.title("Home")
        self.geometry("650x450+350+100")
        btn = Button(self, text="Back", borderwidth=5, command=self.back)
        btn.pack(padx=20, pady=20,side="right",anchor='ne')
        self.container = Frame(self)
        self.container.pack(side = "top", fill = "both", expand = True)
        self.container.grid_rowconfigure(0, weight = 1)
        self.container.grid_columnconfigure(0, weight = 1)
        """ # self.frames = {}
        # for F in (Register, Login, Dashboard):
            # container is the master/parent of all the pages
            # sending self to invoke new Frames
            # frame = F(self.container, self)
            # frame.grid(row = 0, column = 0, sticky ="nsew")
            # self.frames[F] = frame """

        self.show_frame(Login)

    def back(self):
        print(f'back: {self.backStack}')
        if(len(self.backStack) != 0):
            lastFrame = self.backStack.pop()
            self.show_frame(lastFrame)

    def show_frame(self, frame_name):
        # frame = self.frames[frame_name]
        frame = frame_name(self.container, self)
        frame.grid(row = 0, column = 0, sticky ="nsew")
        frame.tkraise()

if __name__ == "__main__":    
    tkApp = App()
    print("Trying to Log In")
    tkApp.mainloop()