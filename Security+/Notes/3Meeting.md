Identification and Authentication
username and password


Authentication Factors
  - Something you...
  - know
  - have
  - are
  - where
  - do

Something you know
  - Passwords
    - Complexity
    - Reuse
    - History
    - Length
    - NIST Guidelines: 8 chars, rate limit, no hints, no commonly used
      - should, strength meter, allow paste, allow unicode, no composition rules

Something you have
  - Keys
  - fobs
  - tokens
  - show one time password (otp)
    - time otp and hmac (secret) otp
  - smart cards
    - embeded certificate for auth
    - encoded meaning
  
Someone you are
  - biometrics
  - voice
  - face

  - biometric errors
    -FAR (False acceptance, really bad)
    -FRR (False rejection, not that bad
    -CER (Higher the value, the worse it is)

Somewhere you are
  - IP
  - GPS


Something you do
  - Handwriting
  - keystroke dynamics
  - mice


Multifactor
  - combining 2 of 5 factors (other that something you do)


Kerberos
  - Network authentication
    - Window Active Directory
  - Several requirements 

  - Ticketing
    - talk to key distribution center get ticket-granting TGTs
  
  - Time synchronization
    - everything must be synced (avoid replay)

LAN Manager
  - new tech that provides CIA to windows
    - first two are bad 

LDAP
  - Lightweight Directory Access Protocol
  - makes things easier

Single Sign On (SSO)

SAML
  - XML based
  - used by web browser
  - trusts other sites that you are authenticated (google)
    - Principle
    - Identity Provider
    - Service Provider

OAuth and OpenID
  - OAuth - a standard for authorization, a framework
  - don't have to sign in a bunch of times

Account Management
  - Least Privilege
  - Need to Know
  - No shared accounts
  - Disabling vs Deleting 
   - Disable unless firing someone
   

Account Types
  - User Accounts
  - Privileged Accounts
  - Guest Accounts
  - Service Accounts

Acces Control Modes
  - Role Based: different departments have different permission
  - Rule Based: firewalls 
  - Discretionary: every file has different permission
  - Mandatory: Classifications
  - Attribute Based: I'm not a robot, multiple levels


  
