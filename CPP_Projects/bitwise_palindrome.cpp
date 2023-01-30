#include <iostream>
#include <bitset>
using namespace std;

unsigned int reverseBits(unsigned int num)
{
    unsigned int count = sizeof(num) * 8 - 1;
    unsigned int reverse_num = num;
      
    num >>= 1; 
    while(num)
    {
       reverse_num <<= 1;       
       reverse_num |= num & 1;
       num >>= 1;
       count--;
    }
    reverse_num <<= count;
    return reverse_num;
}

int main(void){
    int num = 0xF0F00F0F;
    int upper = 0xFFFF0000 & num;
    int lower = 0x0000FFFF & num;
    lower = reverseBits(lower);
    cout<<(upper == lower)<<endl;
}