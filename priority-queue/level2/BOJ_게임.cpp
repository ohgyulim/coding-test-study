#include <iostream>

using namespace std;
#define MAX 1000000000;

long long X, Y, Z;
int cnt=-1;

int main() {
    //입력
    cin >> X >> Y;
    //초기 승률
    Z=(Y*100/X);

    // 99프로 이상인 경우 증가할 수 없음
    if(Z>=99) {
        cout << cnt;
        return 0;
    }

    // 이분 탐색
    int left=0, right= MAX;

    while(left<=right) {
        int mid=(left+right)/2;
        int temp=(Y+mid) * 100 / (X+mid); //새로운 승률 계산

        if(Z<temp) right=mid-1; //승률이 증가 -> mid--
        else left=mid+1; //승률 증가 x -> mid --
    }

    cout << left;
}
