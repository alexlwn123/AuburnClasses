;Alexander Lewin
;10/3/2019
;Comp 3350, Project 2

.386
.model flat,stdcall
.stack 4096
ExitProcess proto, dwExitCode:dword

.data
input BYTE 1,2,3,4,5,6,7,8
shift BYTE 2


.code
main proc

	mov eax, 0
	mov ebx, 0
	mov ecx, 0
	mov edx, 0
	add ah, shift
	add ah, [input]
	add al, shift
	add al, [input+1]
	add bh, shift
	add bh, [input+2]
	add bl, shift
	add bl, [input+3]
	add ch, shift
	add ch, [input+4]
	add cl, shift
	add cl, [input+5]
	add dh, shift
	add dh, [input+6]
	add dl, shift
	add hl, [input+7]

main endp
end main
