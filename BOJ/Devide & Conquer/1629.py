from sys import stdin

def devide(a: int, b: int, c: int):
    if b == 1:
        return a%c

    num = devide(a,b//2,c)

    if b%2 == 1:
        return num**2 * a %c
    else:
        return num**2 % c
        

a,b,c = map(int,stdin.readline().split())
result = devide(a,b,c)

print(result)