Secure Systems
 - Hardening: Making an OS secure
 - Least Functionality: 

Embeded OS
  - Kiosks
  - Networks (routers)

Trusted OS
  - National security

Master Images
  - Secure Starting Point
  - Reduced Costs

Active Directory
  - Centeralized Authentication on windows
  - Same credentials for multiple systems

  Group Policy
    - feature within active directory

Patch and Change Management
  - Updating stuff
  - Hand written change logs

Sandbox
  - Windows Sandbox
  - chroot: linux command to change root, messes up malware

Hardware/ Firmware Security
  - FDE: Full Disk Encryption
  - SED: Self Encrypting Drives
  - TPM: Trusted Platform Module - Key Storage only
  - HSM: Hardware Security Module - improves performance

BIOS, UEFI, Secure Boot
  - BIOS: legacy launcher
  - UEI: Current firmware
  - Secure Boot: insures that firmware is correct

Cloud
------

Location
  - Hosted
    - Must secure communication
    - GCP, AWS, Azure
    - CSP: Cloud Service Provider
    - SLA: Insurance

  - On-Prem
    - Built by organization
    - Lower risk of attack, higher cost

Service Type (As a service)
  - Infrastructure ...
    - Customer pays for virtualized hardware
    - Security is customer's responsibility
    - Can configure OS
    - Not common

  - Platform ...
    - VMs, Web Apps
    - Security is Split between provider and customer

  - Software ...
    - Customer only see end product
    - Security is provider's responsibility 
    - Security as a Service
      - Cloud Access Security Broker (CASB): Middle man between CSP and client (Duo)

Deployment Models
  - Public: for profit (GCP, AWS)
  - Private: On premises (Auburn)
  - Community: Non-profit (Alabama Super Computer Center)
  - Hybrid: All/None of the above

Mobile
------

Deployment Models
  - Corporate Owned
  - Corporate Owned, Personally Enabled: (employees use it for personal stuf)
  - BYOD: bad
  - CYOD (Choose your own devise): Approved list of devices
  - Virtual Desktop Infrastructure

Conncection Methods
  - Cellular
  - Wifi
  - Satcom: Satalite (Insecure)
  - Bluetooth
  - NFC
  - ANT: Proprietary protocols (Fitbit)
  - Infrared
  - USB 

Mobile Device Management (MDM)
  - Passwords, Pins, Biometrics, Screen Locks, Geofencing, 2 Factor
  - Context Aware (Time of Day)

  - Application (White list/ Blacklist)
  - Encryption
  - Storage Segmentation: Isolate data for specific apps
  - Content Management: where content is saved
  - Containerization
  - Remote Wipe
  - Controlling GPS Tagging


Enforcing/ Monitoring
  - Unauthorized Software: Jailbreaking, 3rd Party Apps
  - SMS/MMS: Messaging protocols, Encrypt
  - Payment Gateways, secure credentials
  - Hardware Controls: Disabling Camera/mic

Unauthorized Connections
  - Tethering: shares credentials
  - Wifi direct
  
Embedded
-------

Embedded System: Any device that has a dedicated function
  - Printers, Cars, TVs
  - Not a general purpose machine
  - Responds to external events
  - System on a Chip (SoC): mini computer

Vulnerabilities
  - Maintaining Updates
  
  - IOT
    - Plant malware here to infect netword
    - Wearable Technologies
    - Home automation

Industrial Control Systems 
  - SCADA: Air Gapped

Other
  - Real-time OS (RTOS): links up parts
  - Heating, Ventalation, Air Condition (HVAC): Physical health

Protecting Data
  - Data in Transit: Moving through a network (maybe on a disk)
  - Data at Rest: not moving

  - File permissions: chmod

  - DLP: Data Loss Prevention
    - Policies
    - Data Exfil

