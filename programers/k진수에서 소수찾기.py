import math
def makeZinsu(n: int,k: int) -> str:
    T = "0123456789ABCDEF"
    q, r = divmod(n, k)
    return makeZinsu(q, k) + T[r] if q else T[r]

def findPrime(n: int) -> bool:
    if n <= 1:
        return False

    for i in range(3,int(math.sqrt(n))+1):
        if n%i == 0:
            return False
    
    return True

def solution(n, k):
    zinsu = makeZinsu(n,k)
    zinsu = zinsu.split('0')
    answer = 0
    
    for z in zinsu:
        try:
            num = int(z)
            # print(num)
            if findPrime(num):
                answer += 1
        except:
            pass
    
    return answer