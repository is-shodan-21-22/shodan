<div class="content">
	<div id="settings-page">
		<div class="settings-routines">
			<h1>
				<i class="far fa-address-book"></i>
				Impostazioni di ${user.name}
			</h1>
			
			<div class="button button--unshadow button--alternative settings-status"></div>
			
			<div class="settings-container">
				<div class="set-info">
					<div class="button button--unshadow button--alternative settings-money">
						<strong>
							Hai ${user.money}&euro; nel portafogli.
						</strong>
						
						Non puoi ricaricare il tuo saldo perchè questa piattaforma è una demo.
					</div>
					
					<form onsubmit="tryPasswordChange(); return false" class="psw-form">
						<h2>Cambia la password</h2>
						<input id="settings-input-old-password" type="password" required placeholder="Password attuale">
						<input id="settings-input-new-password" type="password" required placeholder="Nuova password">
						<input id="settings-input-new-password-again" type="password" required placeholder="Ripeti la password">
						<input id="settings-submit-password" class="button button--submit" type="submit">
					</form>
					
					<form onsubmit="tryEmailChange(); return false" class="email-form">
						<h2>Cambia l'email</h2>
						
						<input id="settings-input-email" type="email" required value="${user.email}">
						<input id="settings-submit-email" class="button button--submit" type="submit">
					</form>
				</div>
			</div>
		</div>
	</div>
</div>

<script src="Scripts/SettingsRoutines.js"></script>