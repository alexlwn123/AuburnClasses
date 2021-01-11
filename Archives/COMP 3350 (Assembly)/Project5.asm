; Project 5
; Alexander Lewin
; Date: 12/9/2019
; Summary: This program decrypt and encrypt a message (input) with a key using Vigenere Cipher, the results will be stored in output.


.386
.model flat, stdcall
.stack 4096
ExitProcess PROTO, dwExitCode:DWORD

.data
    input byte "JPUESZSBAPANNHTXRTLBVLL"  
    key byte "ABCXYZ"	
    options BYTE 0                          ; Variable to determine what procedure to execute. 1 for Encryption, otherwise for Decryption
    d byte 26                               ; variable to hold the devisor that will be used for divison ( mod )
    output byte lengthof input dup(0)

.code
    main proc
        cmp options, 1                      ; compare value of options to 1
        je En                               ; if value equal to 1 go to encription rotten
        jne De                              ; if not go to Decription
        De:
            call Decrypt
            jmp MOVEON                      ; after returning from Decrypt procedure jump to "moveon" so we don't call Decrypt accedently
        En:
            call Encrypt
            jmp MOVEON                      ; There is no need use jump but just in case is. 
		
        MOVEON:
            mov eax, 0
            ; Print Output to console: uncomment the next 2 lines to activate (Require irvine32.inc)
            ; lea EDX, output
            ; CALL writestring
            invoke ExitProcess, 0
    main endp
	
    Decrypt proc
        mov esi, 0                          ; index of both input and output
        mov edi, 0                          ; index of the key
        mov ecx, LENGTHOF input             ; (1) loop L (LENGTHOF input) times


        L:
            mov eax, 0                      ; clear EAX
            mov al, input[esi]              ; move cypher character ASCII value into al
            sub al, 65                      ; get the "order" of the character ( A=0, B=1, C=2, ... , Z=25)
            mov bl, key[edi]                ; (2) move the key ASCII character into bl
            sub bl, 65                      ; (3) get the "order" of the character ( A=0, B=1, C=2, ... , Z=25)
            sub al, bl                      ; (4) subtract the indices to "decrypt" the character
            add al, 26                      ; add 26 in case subtraction result is a negative value
            div d                           ; (5) use divide to get "mod 26". AX will be divided, qutent in AL and reminder in AH. 
            add al, 65                      ; (6) add 65 to convert reminder back from order to the proper ASCII value
            mov output[esi], ah             ; write ASCII value to output
            inc esi			
            inc edi
            cmp edi, lengthof key 
            jne Next
            mov edi, 0                      ; (7)if key index reached the end, reset the key index
        Next:
            loop L
            ret
    Decrypt endp

    Encrypt proc
        mov esi, 0
        mov edi, 0
        mov ecx, lengthof input             ; loop L (LENGTHOF input) times
	
        L:
            mov eax, 0
            mov al, input[esi]              ; move the input character into al
            sub al, 65                      ; get the order of the character ( A=0, B=1, C=2, ... , Z=25)
            mov bl, key[edi]                ; move the key character into bl
            sub bl, 65                      ; get the "index" of the character ( A=0, B=1, C=2, ... , Z=25)
            add al, bl                      ; (8) add the indices to "encrypt" the character
            div d                           ; divide so we can "mod 26"
            add ah, 65                      ; add 65 to convert from order back to the proper ASCII value
            mov output[esi], ah             ; write reminder into the output
            inc esi
            inc edi
            cmp edi, lengthof key           ; if we have reached the end of the key ...
            jne Next
            mov edi, 0                      ; ...reset the key index
        Next:
            loop L
            ret
    Encrypt endp
end main

