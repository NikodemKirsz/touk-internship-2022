$port = 8080
$storyColor = "DarkYellow"
$name = "Ziemowit"
$surname = "Gęśląż"
if (![String]::IsNullOrWhiteSpace($args[0])) 
{
    $port = $args[0]
}
if (![String]::IsNullOrWhiteSpace($args[1])) 
{
    $name = $args[1]
}
if (![String]::IsNullOrWhiteSpace($args[2])) 
{
    $surname = $args[2]
}

Write-Host "$name goes online to buy some tickets for today's evening for his family" -ForegroundColor $storyColor
Start-Sleep 2
Write-Host "He lists all of today's (29-08-2022) screenings within 18:00-22:00" -ForegroundColor $storyColor
Start-Sleep 2
Invoke-RestMethod -Method Get "http://localhost:$port/search/screenings?start=2022-08-29_18:00&length=240" | Format-List
Start-Sleep 4

Write-Host "He chooses Pirates of the Caribbean (id: 1) and displays detailed info, especially available seats" -ForegroundColor $storyColor
Start-Sleep 2
Invoke-RestMethod -Method Get "http://localhost:$port/search/screenings/1" | Format-List
Start-Sleep 4

Write-Host "Decides to choose seats: 16, 17, 18, 19" -ForegroundColor $storyColor
Start-Sleep 2
$BodyFailed = @{
    "16" = "ADULT"
    "17" = "ADULT"
    "18" = "CHILD"
    "19" = "CHILD"
}
Invoke-RestMethod -Method Post "http://localhost:$port/reservation/1?name=$name&surname=$surname" -Body ($BodyFailed|ConvertTo-Json) -ContentType "application/json" | Format-List
Start-Sleep 1
Write-Host "He gets surprisingly long and disgusting error message (apparently front-end guys did not do a very good job)" -ForegroundColor $storyColor
Start-Sleep 2
Write-Host "He is a smart fella, though, so he notices his mistake - he left a lonely seat (seatId: 20, last in the row)" -ForegroundColor $storyColor
Start-Sleep 2
Write-Host "So he retakes the reservation attempt choosing seats: 15, 16, 17, 18" -ForegroundColor $storyColor

$BodyPassed = @{
    "15" = "ADULT"
    "16" = "ADULT"
    "17" = "CHILD"
    "18" = "CHILD"
}
Invoke-RestMethod -Method Post "http://localhost:$port/reservation/1?name=$name&surname=$surname " -Body ($BodyPassed|ConvertTo-Json) -ContentType "application/json" | Format-List
Start-Sleep 2
Write-Host "He gets nice (but still undoubtedly too text-like) response with movie title, start time, end time, total price, and chosen seats" -ForegroundColor $storyColor

Read-Host "Click Enter to continue..."

Write-Host "A while later $name texts his friends if they also want to come" -ForegroundColor $storyColor
Start-Sleep 1
Write-Host "And they do - all 3 of them" -ForegroundColor $storyColor
Start-Sleep 1
Write-Host "So he goes to reserve some more tickets" -ForegroundColor $storyColor
Start-Sleep 2

Write-Host "Gets the info about available seats again" -ForegroundColor $storyColor
Start-Sleep 1
Invoke-RestMethod -Method Get "http://localhost:$port/search/screenings/1" | Format-List
Start-Sleep 2

Write-Host "And chooses seats: 18, 19, 20" -ForegroundColor $storyColor
Start-Sleep 1
$BodyFailedAgain = @{
    "18" = "ADULT"
    "19" = "ADULT"
    "20" = "ADULT"
}
Invoke-RestMethod -Method Post "http://localhost:$port/reservation/1?name=$name&surname=$surname" -Body ($BodyFailedAgain|ConvertTo-Json) -ContentType "application/json" | Format-List
Start-Sleep 2
Write-Host "Although his memory isn't great, apparently - seat 18 is already taken (by $name himself)" -ForegroundColor $storyColor
Start-Sleep 2
Write-Host "He corrects his mistake and chooses seats: 14, 19, 20" -ForegroundColor $storyColor
Start-Sleep 1
$BodyPassedAgain = @{
    "14" = "ADULT"
    "19" = "ADULT"
    "20" = "ADULT"
}
Invoke-RestMethod -Method Post "http://localhost:$port/reservation/1?name=$name&surname=$surname" -Body ($BodyPassedAgain|ConvertTo-Json) -ContentType "application/json" | Format-List
Start-Sleep 2
Write-Host "And yet again $name emerges victorius from the immemorial battle of Humans versus Computers" -ForegroundColor $storyColor
Start-Sleep 1