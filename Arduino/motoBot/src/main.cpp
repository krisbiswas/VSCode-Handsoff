#include <Arduino.h>
#include <SoftwareSerial.h>

SoftwareSerial btSerial(2, 3);//Rx, Tx

int FORWARD[]   = {0, 1, 1, 0};
int BACKWARD[]  = {1, 0, 0, 1};
int LEFT[]      = {1, 0, 1, 0};
int RIGHT[]     = {0, 1, 0, 1};
int STOP[]      = {0, 0, 0, 0};

int motor_left_n = 9;
int motor_left_p = 10;
int motor_right_n = 11;
int motor_right_p = 12;

void move(int dir[]){
  for (byte i = 0; i < 4; i=i+1) {
    Serial.print(dir[i]);
    Serial.print(" ");
  }
  digitalWrite(motor_left_n,dir[(byte)0]);
  digitalWrite(motor_left_p,dir[(byte)1]);
  digitalWrite(motor_right_n,dir[(byte)2]);
  digitalWrite(motor_right_p,dir[(byte)3]);
}

void setup() {
  Serial.begin(9600);
  pinMode(motor_left_n, OUTPUT);
  pinMode(motor_left_p, OUTPUT);
  pinMode(motor_right_n, OUTPUT);
  pinMode(motor_right_p, OUTPUT);
  btSerial.begin(9600);
  Serial.println("Setup Ready!!!");
  btSerial.println("Setup Ready!!!");

}

void loop() {
/*   if(btSerial.available()){
    char mvCommand = btSerial.read();
    Serial.print(mvCommand);
    switch (mvCommand){
      case 'f':
        move(FORWARD);
        break;
      case 'b':
        move(BACKWARD);
        break;
      case 'l':
        move(LEFT);
        break;
      case 'r':
        move(RIGHT);
        break;
      
      default:
        move({STOP});
        break;
    }
  }
 */
  digitalWrite(motor_left_n,0);
  digitalWrite(motor_left_p,HIGH);
  digitalWrite(motor_right_n,LOW);
  digitalWrite(motor_right_p,HIGH);

}