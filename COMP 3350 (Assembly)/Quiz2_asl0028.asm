;submitter: Alexander Lewin
;Date: 9/12/19
;Sample program that adds 5 and 6
.386
.model flat,stdcall
.stack 4096
ExitProcess proto,dwExitCode:dword

.code
main proc
	mov	eax,5
	add	eax,6	

	invoke ExitProcess,0
main endp
end main