;Alexander Lewin
;10/25/2019
;Comp 3350, Project 3

.386
.model flat,stdcall
.stack 4096
ExitProcess proto, dwExitCode:dword

.data

input BYTE 1,2,3,4,5,6,7,8
output BYTE SIZEOF input DUP(0)
shift DWORD 3

.code
main PROC

	mov eax, input           ;move input into eax
	mov ecx, lengthof input  ;move lenghtof input into ecx
	sub ecx, shift           ;subtract shift from ecx
	mov edi, shift           ;move shift into destination index 
	mov esi, 0               ;move 0 into source index 


L1:                        ;this loop adds everything that won't wrap arround into output
	mov ebx , [input + esi]  
	mov [output + edi], ebx
	inc esi
	inc edi
loop L1

mov ecx, shift            ;move the offset into the counter
mov edi, 0                ;set destination index to 0

L2:                       ;this loop adds all the "wrap-around" values into output
	mov ebx, [output + esi]
	mov [input + edi], ebx
	inc esi
	inc edi
	loop L2

	invoke ExitProcess, 0  ;exit

main ENDP
END main

