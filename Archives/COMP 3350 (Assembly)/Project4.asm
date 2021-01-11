; Author: Alexander Lewin

.386
.model flat,stdcall
.stack 4096
ExitProcess proto,dwExitCode:dword

.data
    s1 byte "GARDEN"
    s2 byte "DANGER"
    c1 byte 26 dup(0)               ;counter for each letter in s1
    c2 byte 26 dup(0)               ;counter for each letter in s2
.code
    main proc
				
				mov ah, LENGTHOF s1         ;stores length of s1
				mov al, LENGTHOF s2         ;stores length of s2

				cmp ah, al                 ;immediatly exists if lengths are not equal
				jne Bad

        mov ecx, LENGHTOF s1        ;iterate though the length of the string
        mov esi, 0                  ;start at the first byte of s1 and s2
				mov ebx, 0                  ;Keeps running total of difference between words 
																    ;At the end, if ebx is 0, we know that the words must have had the same letters	

        CounterLoop:                ;this will traverse through s1 and s2 

            movzx edi, s1[esi]      ;move the value from s1 into edi
            add ebx, edi            ;add edi into ebx
            movzx edi, s2[esi]      ;move the value from s2 into edi
            sub ebx, edi            ;subtract edi from ebx
            inc esi                 ;increment esi
            loop CounterLoop        ;Once the loop is over, ebx will be zero if and only if s1 and s2 had the same letters 
				

				cmp ebx, 0                  ; If ebx == 0, then the words are anagrams 
				je Good              
				jne Bad

				Good:
            mov eax, 1              ;Signifies there is an anagram
						jmp NoAna

				Bad:
						mov eax, 0              ;Signifies there is no anagram``
						jmp NoAna

        NoAna:
            invoke ExitProcess, 0

    main endp
end main
