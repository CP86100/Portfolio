# Corentin Pinato
# This is program has to find the decryption key of an ElGamal encryption method by using a brute force strategy.

#publicKey = [8347852664524685394671539,1725419847553,6544719840647906985061541]
#message = [2030735454253481748649532,1340127105753313239218903]

publicKey = [29, 2, 3]
message = [23, 27]

#publicKey = [24852977,2744,8414508]
#message = [15268076,743675]

def decrypt(publicKey, message, privateKey):
    p = publicKey[0]
    g = publicKey[1]
    gxmodp = publicKey[2]

    gy = message[0]
    mgxy = message[1]

    c1 = (gy**(p-1-privateKey))%p
    c2 = (mgxy*c1)%p

    return c2

def encrypt(message,publicKey,randomNum):
    p = publicKey[0]
    g = publicKey[1]
    gxmodp = publicKey[2]

    m1 = (g**randomNum)%p
    m2 = (message*(gxmodp**randomNum))%p

    result = [m1,m2]

    return result

def modPow(number,power,mod):

    if(power == 0):
        return 1
    elif(power%2==0):
        halfpower=modPow(number,(power/2),mod)
        return modMult(halfpower,halfpower,mod)
    else:
        halfpower=modPow(number,((power)/2)+1,mod)
        firstbit = modMult(halfpower,halfpower,mod)
        return modMult(firstbit,number,mod)


def modMult(first,second,mod):
    if(second==0):
        return 0
    elif(second%2==0):
        half=modMult(first,(second/2)+1,mod)
        return(half+half)%mod
    else:
        half=modMult(first,((second)/2)+1,mod)
        return(half+half+first)%mod


Message = 1

encrypted = encrypt(Message,publicKey,2)
print(encrypted)

key = 1

decrypted = decrypt(publicKey,encrypted,key)
print("Tryed key = "+str(key))

while(decrypted != Message):
    key += 1
    decrypted = decrypt(publicKey,encrypted,key)
    print("Tryed key = "+str(key))

print("The key is: "+str(key))

print("So the secret Message is: "+str(decrypt(publicKey,message,key)))