# Build Hebrew Fix for all Minecraft versions
# Run from the project root directory

$ErrorActionPreference = "Stop"
$ScriptDir = Split-Path -Parent $MyInvocation.MyCommand.Path

$Targets = @(
    @{Name="1.21.11 (Yarn)"; Dir="fabric-1.21.11"},
    @{Name="26.x (Mojang)"; Dir="fabric-26.2"}
)

Write-Host "=== Hebrew Fix Multi-Version Build ===" -ForegroundColor Cyan
Write-Host ""

foreach ($target in $Targets) {
    Write-Host "Building $($target.Name)..." -ForegroundColor Yellow
    Push-Location (Join-Path $ScriptDir $target.Dir)
    try {
        ..\gradlew build --no-daemon
        if ($LASTEXITCODE -ne 0) {
            Write-Host "FAILED: $($target.Name)" -ForegroundColor Red
            exit 1
        }
        Write-Host "OK: $($target.Name)" -ForegroundColor Green
    }
    finally {
        Pop-Location
    }
    Write-Host ""
}

Write-Host "=== All builds successful ===" -ForegroundColor Green
Write-Host ""
Write-Host "JARs:" -ForegroundColor Cyan
Get-ChildItem "$ScriptDir\fabric-1.21.11\build\libs\*.jar" | ForEach-Object { Write-Host "  1.21.x: $($_.Name)" }
Get-ChildItem "$ScriptDir\fabric-26.2\build\libs\*.jar" | ForEach-Object { Write-Host "  26.x:   $($_.Name)" }
